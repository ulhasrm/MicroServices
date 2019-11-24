import { Workflow } from './workflow';
import { LoanStatus } from './loanstatus';

export class WorkflowTransition {
id: number;
name: string;
decription: string;
active: boolean;
commentRequired: boolean;
workflow: Workflow;
from: LoanStatus;
to: LoanStatus;
}
