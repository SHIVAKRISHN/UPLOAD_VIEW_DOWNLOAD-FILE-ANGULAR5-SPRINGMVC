import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestMethod, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';


import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';

@Injectable()
export class UploadService {

  public user: any[]
  public FileContent: Blob;


  constructor(private _http: Http) { }


  baseURI: string = 'http://localhost:8080/Upload_view_download_service_SpringMvc';


  saveUser(userObject) {

    let userData = JSON.stringify(userObject);

    let requestOptions = new RequestOptions({ method: RequestMethod.Post });
    return this._http.post(this.baseURI + "/saveUser", userObject, requestOptions).map(responseObj => responseObj.json());
  }

  getUsers() {

    let headerOptions = new Headers({ 'Content-Type': 'application/json' });
    let requestOptions = new RequestOptions({ method: RequestMethod.Get, headers: headerOptions });

    return this._http.get(this.baseURI + "/getUsers", requestOptions).subscribe(res => {

      this.user = res.json();
      //console.log("the data is"+this.user);
      res.json()
    });
  }

  viewFileInTxtContent(FileName) {
    this.FileContent = null;
    let headerOptions = new Headers({ 'Content-Type': 'application/json' });
    let requestOptions = new RequestOptions({ method: RequestMethod.Get, headers: headerOptions });

    return this._http.get(this.baseURI + "/viewFile/" + FileName + "/", requestOptions).subscribe((responseObj => this.FileContent = responseObj.json().response));

  }


  deleteUser(userId) {

    let headerOptions = new Headers({ 'Content-Type': 'application/json' });
    let requestOptions = new RequestOptions({ method: RequestMethod.Post, headers: headerOptions });


    return this._http.post(this.baseURI + "/deleteUser/"+userId,requestOptions).map(responseObj => responseObj.json());
  }



}
