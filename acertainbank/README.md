The Scenario:

Consider a bank which  needs  to perform  risk  analysis  against a set  of  accounts. The  accounts  are  partitioned  among multiple branches; in other words, each branch contains multiple accounts, but each account belongs to 
exactly one branch.

An account is identified by both the branch identifier and an account identifier. The bank  needs  to  manage each  account (e.g., process  debits  and  credits  to balances)  and  to  calculate the exposure  of  each  branch  to lending risk. These risks are  reassessed  often  to  allow  branches  to  make further lending and borrowing decisions during their daily operation. Operations to manage accounts or calculate exposure may arrive at any time, so we cannot make any assumptions about whether operations can be shifted in time (e.g., processing updates only at night is not allowed by our bank).
