import { Component, OnInit } from '@angular/core';
import { Dinosaur } from '../models/dinosaur.model';
import { DinoService } from '../services/dino-service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  content: string;
  constructor(private userService: UserService) { }
  ngOnInit() {
    this.userService.getPublicContent().subscribe(
      data => {
        this.content = data;
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );
  }

}
