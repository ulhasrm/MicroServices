import { LoanStatus } from './loanstatus';

export class LoanType {
	id: string;
	name: string
	active: boolean;
	defaultStatus: LoanStatus;
}
