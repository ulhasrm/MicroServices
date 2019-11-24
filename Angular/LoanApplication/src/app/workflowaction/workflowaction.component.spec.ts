import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkflowactionComponent } from './workflowaction.component';

describe('WorkflowactionComponent', () => {
  let component: WorkflowactionComponent;
  let fixture: ComponentFixture<WorkflowactionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkflowactionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkflowactionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
