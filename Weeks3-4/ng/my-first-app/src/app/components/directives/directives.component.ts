import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-directives',
  templateUrl: './directives.component.html',
  styleUrls: ['./directives.component.css']
})
export class DirectivesComponent implements OnInit {
  showIf= true;

  showIf = true;

  constructor() { }

  ngOnInit() {
  }
  toggleIf(){
    this.showIf = !this.showIf;
  }

  toggleIf() {
    this.showIf = !this.showIf;
  }

}
