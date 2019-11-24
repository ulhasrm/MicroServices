import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkflowviewComponent } from './workflowview.component';

describe('WorkflowviewComponent', () => {
  let component: WorkflowviewComponent;
  let fixture: ComponentFixture<WorkflowviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkflowviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkflowviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
