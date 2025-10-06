import { Injectable } from '@angular/core';
import { Client, IMessage, Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { Observable, Subject } from 'rxjs';
import { env } from '../../env/environment';

@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  private client: Client;
  private connected: boolean = false;
  private messageSubject = new Subject<string>();

  constructor() {
    this.client = new Client({
      webSocketFactory: () => new SockJS(env.wsUrl),
      reconnectDelay: 5000,
    });

    this.client.onConnect = (frame) => {
      console.log('Connected: ', frame);
      this.connected = true;
    };

    this.client.onStompError = (frame) => {
      console.error('Broker error: ', frame);
    };

    this.client.activate();
  }

  subscribeToTopic(topic: string): Observable<string> {
    return new Observable<string>((observer) => {
      const doSub = () => {
        this.client.subscribe(topic, (message) => {
          observer.next(message.body);
        });
      };
      if (this.connected) {
        doSub();
      } else {
        const checkConnection = setInterval(() => {
          if (this.connected) {
            clearInterval(checkConnection);
            doSub();
          }
        }, 100);
      }
    });
  }

  private doSubscribe(topic: string, subject: Subject<string>) {
    this.client.subscribe(topic, (message: IMessage) => {
      subject.next(message.body);
    });
  }

  sendMessage(message: string) {
    if (this.client.connected) {
      this.client.publish({
        destination: '/app/hello',
        body: message,
      });
    }
  }

  get messages$(): Observable<string> {
    return this.messageSubject.asObservable();
  }
}