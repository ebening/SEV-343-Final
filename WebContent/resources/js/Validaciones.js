function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : event.keyCode;
    if (charCode > 31 && (charCode != 46 &&(charCode < 48 || charCode > 57)))
        return false;
    return true;
}

function onlyNumber(component)
{
var x=document.getElementById(component);
if(isNaN(x.value))
x.value ="";
}