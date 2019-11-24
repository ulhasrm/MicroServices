import { LoanStatus } from './loanstatus';
import { LoanType } from './loantype';

export class Application {
id: number;
userId: number;
loanType: LoanType;
status: LoanStatus;
applicationDate: Date;
updatedDate: Date;
amount: DoubleRange;
amountApproved: DoubleRange;
}
