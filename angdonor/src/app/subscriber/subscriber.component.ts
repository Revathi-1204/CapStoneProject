import { Component, OnInit } from '@angular/core';
import { SubscriberService } from '../subscriber.service';
import { Request } from '../request';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-subscriber',
  templateUrl: './subscriber.component.html',
  styleUrls: ['./subscriber.component.css']
})

export class SubscriberComponent {
  request: Request = {
    name: '',
    upc: '',
    number: '',
    date: new Date(),
    status :'',
    address : '',
  };

  constructor(private subscriberService: SubscriberService) {}

  reset(){
    this.request.name='',
    this.request.address='',
    this.request.status='',
    this.request.upc='',
    this.request.number=''
  }

  submitRequest() {
    this.subscriberService.addSubscriber(this.request).subscribe(
    (response: Request) => {
      this.request = response;
      this.reset();
    },
    (error: any) => {
      this.reset();
    }
    )};

}
