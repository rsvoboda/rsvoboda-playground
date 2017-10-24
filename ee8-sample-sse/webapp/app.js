
class Events {

    constructor() {
        this.events = new EventSource("http://127.0.0.1:8080/ee8-sample-sse/resources/beats");
        this.events.onopen = (e) => console.log(e);
        //this.events.onmessage = (e) => console.log(e);
        //this.events.onmessage = ({data, target}) => console.log(data + " - " + target.url);
        //this.events.onmessage = ({data, target}) => console.log("%c" + data + " - " + target.url, 'background:DarkOrange');
        this.events.onmessage = ({data, target}) => console.log(`%c${data}  - ${target.url}`, 'background:LightGreen');
    }

}

new Events();