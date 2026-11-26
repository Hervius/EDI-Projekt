package de.thi.informatik.edi.pubsub;


import lombok.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/broker")  // -> http://localhost:8080/broker
public class MessageBrokerController {

    private final MessageBroker broker;
    private final Map<String, Topic> topics = new HashMap<>();

    private final Map<UUID, List<Event>> events = new HashMap<>();

    public MessageBrokerController(MessageBroker messageBroker) {
        this.broker = messageBroker;
    }

    // Idee: Virtuelle Subscription hinterlegen mit ID, alle Events für die ID Buffern, neue HTTP-Router
    // kann mit ID dann Buffer abfragen
    // http://localhost:8080/broker/subscribe/test-topic
    @GetMapping("/subscribe/{topic}")
    public String subscribe(@PathVariable String topic) {
        Topic t = getOrCreate(topic);


        UUID id = UUID.randomUUID();
        List<Event> list = this.events.put(id, new ArrayList<>());
        broker.subscribe(t, (Event event) -> {
            list.add(event);
        });

        return  id.toString();

    }

    private @NonNull Topic getOrCreate(String topic) {
        Topic t = topics.get(topic);
        if(t == null) {
            t = new Topic(topic);
            topics.put(topic, t);
        }
        return t;
    }

    // http://localhost:8080/broker/subscribe/test-topic?message=HelloWorld
    @GetMapping("/publish/{topic}")
    public void publish(@PathVariable String topic, @RequestParam String message) {

        Topic t = getOrCreate(topic);
        this.broker.publish(t, new MessageEvent(message));

    }

    @GetMapping("/poll/{id}")
    public ArrayList<Event> poll(@PathVariable String id) {
        List<Event> list = this.events.get(id);
        if(list != null) {
            ArrayList<Event> bufferedEvents = new ArrayList<>(list);
            list.clear();
            return bufferedEvents;
        }
        return new ArrayList<>();
    }

    // Was wäre nötig, denkbar den Broker auf HTTP-Basis umzusetzen
    // -> HTTP ist zustandslos, man würde mit jeder Anfrage genau ein Paket
    // als Antwort senden
    // -> Anfrage vom Client zum Server, Antwort vom Server an Client

    // Warum ist ein Pub/Sub daher schwierig?
    // -> man braucht mehrere Pakete
    // -> subscription vom Client, aber dann müsste der Server den Client mit Event
    // informieren

    // Wir müssen "Polling" betreiben, regelmäßig anfragen und die
    // Subscription sollte eine Art ID liefern, für die man anfragt



}
