import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplicationdetailuserComponent } from './applicationdetailuser.component';

describe('ApplicationdetailuserComponent', () => {
  let component: ApplicationdetailuserComponent;
  let fixture: ComponentFixture<ApplicationdetailuserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ApplicationdetailuserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApplicationdetailuserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
