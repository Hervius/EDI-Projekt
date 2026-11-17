package de.thi.informatik.edi.pubsub;

import org.apache.logging.log4j.message.Message;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MessageBroker {

    private Map<Topic, List<Subscriber>> subscribers = new HashMap<>();     // Wie könnte man dazu kommen?
    private Map<Topic, Queue<Event>> messages = new HashMap<>();     // Warum Queue?

    void subscribe(Topic topic, Subscriber subscriber){
        List<Subscriber> subscriberList = subscribers.get(topic);
        if(subscriberList == null){
            subscriberList = new ArrayList<>();
            subscribers.put(topic, subscriberList);
        }
        subscriberList.add(subscriber);
    }

    void unsubscribe(Topic topic, Subscriber subscriber){
        List<Subscriber> subscriberList = subscribers.get(topic);
        if(subscriberList != null){
            subscriberList.remove(subscriber);
            if(subscriberList.isEmpty()){
                subscribers.remove(topic);
            }
        }


    }


    void publish(Topic topic, Event event){
        // Einführen einer Queue, in die Nachrichten auflaufen
        Queue<Event> events = this.messages.get(topic);
        if(events == null){
            events = new LinkedList<>();
            this.messages.put(topic, events);
        }
        events.add(event);

        // Falls es subscriber gibt, einen auswählen und die events weiter geben; falls noch mehr da ist, auch diese abarbeiten
        // ggf. parallele Ausführung denkbar
        List<Subscriber> subscriberList = subscribers.get(topic);
        if(subscriberList != null){
            while(!events.isEmpty()) {
                Event poll = events.poll();

                for (Subscriber subscriber : subscriberList) {
                    try {
                        subscriber.onEvent(poll);
                        break;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static class TempEvent extends Event {
        private double value;
        public TempEvent(double value){
            this.value = value;
        }

        public double getValue(){
            return value;
        }

        public void setValue(double value){
            this.value = value;
        }

        public String toString() {
            return "TempEvent [value=" + value + "]";
        }
    }

    public static void main(String[] args){

        MessageBroker broker = new MessageBroker();
        //Topic topic = new Topic("temperature");
        Topic topic1 = new Topic("temperature-building-d");
        Topic topic2 = new Topic("temperature-building-c");
        broker.publish(topic1, new TempEvent(22));
        Subscriber sub1 = (Event event) -> System.out.println("A: " + event);
        broker.subscribe(topic1, sub1);
        broker.subscribe(topic2, sub1);
        broker.subscribe(topic1, (Event event) -> System.out.println("B: " + event));
        broker.subscribe(topic1, (Event event) -> System.out.println("C: " + event));
        broker.publish(topic1, new TempEvent(21));
        broker.publish(topic2, new TempEvent(19));
    }

}