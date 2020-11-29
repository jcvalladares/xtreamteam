import { Component, OnDestroy } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AppService } from './app.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnDestroy {

  constructor(private appService: AppService) {}

  title = 'angular-nodejs-example';

  userForm = new FormGroup({
    firstName: new FormControl('', Validators.nullValidator && Validators.required),
    middleName: new FormControl('', Validators.nullValidator),
    lastName: new FormControl('', Validators.nullValidator && Validators.required),
    email: new FormControl('', Validators.nullValidator && Validators.required),
	password: new FormControl('', Validators.nullValidator && Validators.required),
	birthDate: new FormControl('', Validators.nullValidator),
	type: new FormControl('USER', Validators.nullValidator),
	isValidated: new FormControl('N', Validators.nullValidator),
	isQRCodeGenerated: new FormControl('N', Validators.nullValidator)
  });

  users: any[] = [];
  userCount = 0;

  destroy$: Subject<boolean> = new Subject<boolean>();

  onSubmit() {
    this.appService.addUser(this.userForm.value, this.userCount + 1).pipe(takeUntil(this.destroy$)).subscribe(data => {
      console.log('message::::', data);
      this.userCount = this.userCount + 1;
      console.log(this.userCount);
      this.userForm.reset();
    });
  }

  getAllUsers() {
    this.appService.getUsers().pipe(takeUntil(this.destroy$)).subscribe((users: any[]) => {
		this.userCount = users.length;
        this.users = users;
    });
  }

  ngOnDestroy() {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
  }

  ngOnInit() {
	this.getAllUsers();
  }
}