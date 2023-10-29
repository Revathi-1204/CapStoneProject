import { Component, OnInit } from '@angular/core';
import { StatusService } from '../status.service';
import { StatusDto } from '../status-dto';

@Component({
  selector: 'app-status',
  templateUrl: './status.component.html',
  styleUrls: ['./status.component.css']
})
export class StatusComponent{
  
  mobileNumber: String = '';
  statusdto :StatusDto ={status:'',reason:''}

  constructor(private statusService: StatusService) {}

  getStatus() {
    this.statusService.getStatus(this.mobileNumber).subscribe(
      (response: StatusDto) => {
        console.log(response.status);
        this.statusdto = response;
      },
      (error: any) => {
        console.error(error);
      }
    );
  }

}
