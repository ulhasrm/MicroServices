import { LoanStatus } from './loanstatus';

export class WorkFlowActionResult {
applicationId: number;
transactionId: number;
status: LoanStatus;
success: boolean;
message: string;
}
