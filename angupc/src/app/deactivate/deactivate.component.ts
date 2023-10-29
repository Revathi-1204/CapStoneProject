import { Component, OnInit } from '@angular/core';
import { StatusService } from '../status.service';
import { Request } from '../request';

@Component({
  selector: 'app-deactivate',
  templateUrl: './deactivate.component.html',
  styleUrls: ['./deactivate.component.css']
})
export class DeactivateComponent implements OnInit{
  mobileNumber : String = '';
  requests: Request[] = [];
  request:Request={name:'',upc:'',number:'',address:'',date:new Date(),status:'',isButtonDisabled:false};
    constructor(private statusService: StatusService) {}

    ngOnInit(): void {
        this.statusService.getClearedRequests().subscribe(
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
  
    deletePortingRequest(mobileNumber : String) {
      this.statusService.deletePortingRequest(mobileNumber).subscribe(
        () => {
          console.log('Porting request deleted successfully.');
        },
        (error) => {
          console.error('Error deleting porting request:', error);
        }
      );
    }
  
   
  }