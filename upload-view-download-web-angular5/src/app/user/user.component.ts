import { Component, OnInit } from '@angular/core';
import { NgbModal, ModalDismissReasons, NgbModalRef, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';
import { NgForm } from '@angular/forms/src/directives/ng_form';
import { UploadService } from './upload.service';
import { DomSanitizer } from '@angular/platform-browser';


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
  providers: [UploadService]
})
export class UserComponent implements OnInit {

  closeResult: string;

  Username: string;
  Emailid: string;
  MobileNumber: string;
  Gender: string;
  public imageToUpload: File;
  public documentToUpload: File;
  public user: any[]
  public UrlOfTheFile: string;
  public id_of_user : string;


  private modalRef: NgbModalRef;

  constructor(private modalService: NgbModal, private _UploadService: UploadService, public sanitizer: DomSanitizer) {

  }
  ngOnInit() {

    this.clear();
    this._UploadService.getUsers().add(data => { })


  }

  public fileChangeEvent_doc(fileInput: any) {

    if (fileInput.target.files && fileInput.target.files[0]) {
      var reader = new FileReader();

      this.documentToUpload = (fileInput.target.files[0]);



      console.log("the selected document name is  ::: " + JSON.stringify(fileInput.target.files[0].name));
      reader.readAsDataURL(fileInput.target.files[0]);

    }
  }


  public saveUser() {

    let formData = new FormData();

    formData.append("userName", this.Username);
    formData.append("emailId", this.Emailid);
    formData.append("mobileNumber", this.MobileNumber);
    formData.append("gender", this.Gender);
    formData.append('user_document', this.documentToUpload);

    this._UploadService.saveUser(formData).subscribe(data => {
      this.ngOnInit();
    });


  }




  viewFileInTxt(content, nameOfTheFile) {

    let ngbModalOptions: NgbModalOptions = {
      backdrop: 'static',
      keyboard: false,
      size: 'lg'

    };
    this.modalService.open(content, ngbModalOptions);

    this._UploadService.viewFileInTxtContent(nameOfTheFile).add(data => { });


  }



  viewFileInPdf(content, nameOfTheFile) {

    let ngbModalOptions: NgbModalOptions = {
      backdrop: 'static',
      keyboard: false,
      size: 'lg'

    };
    this.modalService.open(content, ngbModalOptions);
    this.UrlOfTheFile = this._UploadService.baseURI + "/file/" + nameOfTheFile;



  }

  downloadFile(FileName) {
    return this._UploadService.baseURI + "/file/" + FileName;

  }
  clear()
  {
    this.Username="";
    this.Emailid="";
    this.MobileNumber="";
    this.Gender="";
    ( < HTMLInputElement > document.getElementById("Upload_document")).value= null;
  }


  deleteUser(content, idOfTheUser) {

    let ngbModalOptions: NgbModalOptions = {
      backdrop: 'static',
      keyboard: false,
      size: 'sm'

    };

    this.id_of_user=idOfTheUser;
    this.modalRef=this.modalService.open(content, ngbModalOptions);
  
  }


  deleteUserResult()
  {

    this._UploadService.deleteUser(this.id_of_user).subscribe(data =>{ this.ngOnInit(); })
    this.modalRef.close();

}

}
