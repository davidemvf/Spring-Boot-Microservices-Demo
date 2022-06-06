import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Dinosaur } from "../models/dinosaur.model";

@Injectable({providedIn: "root"})
export class DinoService {
    url :string = "http://localhost:8080/dinosaurs"

    constructor(private httpClient :HttpClient) {

    }

    getDinoList() {
        return this.httpClient
            .get(this.url)
            .pipe(
                map(res => {
                    console.log(res);
                    let dinosaurs : any;
                    dinosaurs = res;
                    console.log(dinosaurs);
                    
                    return dinosaurs;
                })
            )
    }
}