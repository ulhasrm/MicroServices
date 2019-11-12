import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplicationdetailComponent } from './applicationdetail.component';

describe('ApplicationdetailComponent', () => {
  let component: ApplicationdetailComponent;
  let fixture: ComponentFixture<ApplicationdetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ApplicationdetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApplicationdetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
