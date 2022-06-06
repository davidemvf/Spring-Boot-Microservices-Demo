import { Component, OnInit } from '@angular/core';
import { User } from '../models/user.model';
import { TokenStorageService } from '../services/token-storage.service';
@Component({
  selector: 'app-profile',
  templateUrl: './profile-details.component.html',
  styleUrls: ['./profile-details.component.scss']
})
export class ProfileDetailsComponent implements OnInit {
  
  currentUser: User;
  
  constructor(private tokenSTorService: TokenStorageService) { }
  
  ngOnInit() {
    this.currentUser = this.tokenSTorService.getUser();
  }
}
