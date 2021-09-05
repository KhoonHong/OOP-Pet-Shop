In the pet shop reservation system, there are login for owner, employee, customer, customer sign-up and exit program.

User can create new account through customer sign-up.
The predefined login credentials are as below:
----owner----
username :  o
password :  o
functions :
   ->employee	: add, edit, display, remove employee records
   ->promotion	: add, edit, display, remove promotion records
   ->search		: search records of employee, customer, reservation, bill, bill history
   ->report		: display different type of report
   ->display	: display records of employee, customer, reservation, bill, bill history
   ->user profile	: display, edit profile, delete account
   ->log out

----employee----
username :  e
password :  e
functions :
   ->schedule	: work schedule and reservation details
   ->search customer : search customers records by name
   ->search reservation : search reservation records by date or customers name
   ->user profile	: display, edit profile

----customer----
username :  c
password :  c
function :
   ->reservation	: add, edit, cancel, display reservation
   ->billing	: check out, search and display billing history, display billing
   ->pet		: add, edit, remove, display pet
   ->services available : description of all four services provided
   ->user profile	: display, edit profile, delete account, billing card(add, display, remove, edit)
basic flow :
customer sign up(no account) -> customer log in -> add pet (if not yet register a pet ,pet owned can > 1) -> edit pet (if required) -> add reservation (can > 1) -> edit pet / reservation(if required) -> billing -> check out -> log out
