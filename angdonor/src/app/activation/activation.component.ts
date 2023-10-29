import { Component, OnInit } from '@angular/core';
import { SubscriberService } from '../subscriber.service';
import { Subscriber } from '../subscriber';

@Component({
  selector: 'app-activation',
  templateUrl: './activation.component.html',
  styleUrls: ['./activation.component.css']
})
export class ActivationComponent {
  mobileNumber: string;
  subscriber : Subscriber;
  showCelebration : boolean = false;

  constructor(private subscriberService: SubscriberService) {}
  activateSubscriber() {
    this.subscriberService.activateSubscriber(this.mobileNumber).subscribe(
      (data : Subscriber) => {
        this.subscriber = data;
        this.showCelebration = true;
        setTimeout(() => {
          this.showCelebration = false;
        }, 3000);
    });

  }
}