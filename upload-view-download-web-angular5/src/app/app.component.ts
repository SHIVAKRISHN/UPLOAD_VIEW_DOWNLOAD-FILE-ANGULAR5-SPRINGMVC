import { Component } from '@angular/core';
import {NgbModal,ModalDismissReasons,NgbModalRef,NgbModalOptions} from '@ng-bootstrap/ng-bootstrap';
import { NgForm } from '@angular/forms/src/directives/ng_form';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent  {
  title = 'app';
  closeResult: string;

  Username : string;
  Emailid : string;
  MobileNumber : string;



  private modalRef1:NgbModalRef;

  constructor(private modalService: NgbModal) {
    
     } 

  openPopup(content, ope) {
   let ngbModalOptions: NgbModalOptions = {
     backdrop : 'static',
     keyboard : false
   
   };
   this.modalRef1=this.modalService.open(content,ngbModalOptions);
 }

 open(content) {

  let ngbModalOptions: NgbModalOptions = {
    backdrop : 'static',
    keyboard : false,
    size :'lg'
  
  };

  this.modalService.open(content,ngbModalOptions)
 }
/*  getDismissReason(reason: any): string {
  if (reason === ModalDismissReasons.ESC) {
    return 'by pressing ESC';
  } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
    return 'by clicking on a backdrop';
  } else {
    return  `with: ${reason}`;
  }
}

 */
}
