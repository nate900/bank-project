/******************************************************************************
 * Extra Creadit, Lab# 2, Java III, CRN: 40876
 * Author: Josiah Martin
 * Date: 20 January 2022
 * Due: 24 January 2022
 *******************************************************************************/

//method to validate the data in the form
function validateForm(){
    //If, and only if, all data fields are correctly validated, then this method returns true
    if(validateTextFields() && validateNumFields()) return true;
    //This line executes if validation was unsuccessful
    return false;
}

function validateTextFields(){
    var custId = document.loginForm.custIdField.value;
    var passwordField = document.loginForm.passwordField.value;
    var isValid = true;
    
    //Tests to see if both the customer ID field and password field are empty
    if(custId === "" && passwordField === ""){
        alert("You must type a customer ID and your password");
        isValid = false;
        //Test to see if the customer ID field is empty
    } else if(custId === ""){
        alert("You must type a customer ID");
        isValid = false;
        //Test to see if the password field is empty
    } else if(passwordField === ""){
         alert("You must enter your password");
         isValid = false;
    }
    //If all tests are false, then the textfields must contain valid data
    return isValid;
}

function validateNumFields(){
    var custId = document.loginForm.custIdField.value;
    var passwordField = document.loginForm.passwordField.value;
    var isValid = true;
    //Test to see if the customer ID is not a number
    if(isNaN(custId)){
        alert("The customer ID must be a number");
        isValid = false;
    }
    //Test to see if the customer ID is in the right range of numbers
    if(custId > 3999 || custId < 3000){
        alert("Your customer ID must be a number between 3000 and 3999");
        isValid = false;
    }
    return isValid;
}

