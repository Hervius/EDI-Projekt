package de.thi.informatik.edi.pubsub;

public class Topic {

    private String name;

    public Topic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Topic topic){
        return this.name.equals(topic.name);
    }

    public int hashCode(){
        return this.name.hashCode();
    }

}
