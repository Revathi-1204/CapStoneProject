import { Component, OnInit } from '@angular/core';
import { Request } from '../request';
import { SubscriberService } from '../subscriber.service';
import { Status } from '../status';

@Component({
  selector: 'app-status',
  templateUrl: './status.component.html',
  styleUrls: ['./status.component.css']
})
export class StatusComponent{

  mobileNumber: string;
  // status: Donor;
  error: String;
  request : Request;
  constructor(private subscriberService: SubscriberService) {}


  checkPortingRequestStatus() {

    this.subscriberService.getSubscriberByNumber(this.mobileNumber).subscribe(
      (response) => {
        this.request = response;
        console.log(this.request);
        this.error = '';
      },
      (error) => {
        console.log(error);
        // this.subscriber = '';
        this.error = 'Porting request not found for the provided mobile number.';
      }
    );
  }
}


