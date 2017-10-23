//console.log("Hello ...")

class Events {

    constructor() {
        this.events = new EventSource("http://127.0.0.1:8080/ee8-sample-sse/resources/beats");
        this.events.onopen = (e) => console.log(e);
        this.events.onmessages = (e) => console.log(e);
    }

}

new Events();