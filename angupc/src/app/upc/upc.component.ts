import { Component} from '@angular/core';
import { UPCResponse } from './UPCResponse';
import { UpcService } from '../upc.service';

@Component({
  selector: 'app-upc',
  templateUrl: './upc.component.html',
  styleUrls: ['./upc.component.css']
})
export class UpcComponent {

  mobileNumber: string = '';
  generatedUpc: UPCResponse = {"upc": ""};
  show : number = 0;

  constructor(private upcService: UpcService) {}

  generateUpc() {
    this.upcService.generateUpc({ number: this.mobileNumber }).subscribe(
      (response: UPCResponse) => {
        console.log(response.upc);
        this.generatedUpc = response;
        this.show = 1;
      },
      (error: any) => {
        this.show=2;
        console.error(error);
        // Handle error
      }
    );
  }
}