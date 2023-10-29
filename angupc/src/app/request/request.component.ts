import { Component, OnInit } from '@angular/core';
import { Request } from '../request';
import { StatusService } from '../status.service';

@Component({
  selector: 'app-request',
  templateUrl: './request.component.html',
  styleUrls: ['./request.component.css']
})
export class RequestComponent implements OnInit {

  requests: Request[] = [];
  request:Request={name:'',upc:'',number:'',address:'',date:new Date(),status:'',isButtonDisabled:false};
  constructor(private requestService: StatusService) { }

  ngOnInit(): void {
    this.requestService.getAllRequests().subscribe(
      (data: Request[]) => {
      this.requests = data;
      if (data && data.length > 0) {
        this.request = data[0];
      }
    },
    (error) => {
      console.error('Error:', error);
    });
  }
  forwardRequest(request: Request) {
    if (!request.isButtonDisabled) {
      this.verification(request);
      console.log(request);
      request.isButtonDisabled = true;
    }
  }
  verification(request:Request){
    this.requestService.getStatus(request.number);
  }
}
