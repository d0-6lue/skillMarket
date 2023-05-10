const adminLogin =  document.querySelectorAll(".admin_login_input");
const adminId =  document.querySelector("input[name=adminId]");
const adminPwd =  document.querySelector("input[name=adminPwd]");
const submit = document.querySelector(".submit");

submit.disabled = true;

adminLogin.forEach(function (event) {
    event.addEventListener("blur", (e)=>{
        console.log(adminPwd.value);
        if(e.target.value === ""|| adminId.value === "" || adminPwd.value === "") {
            event.style.borderColor = "#F36969";
            event.style.borderWidth = "3px";
            submit.disabled = true;
        }
        
        else {
            event.style.borderColor = "#585858"
            event.style.borderWidth = "0";
            submit.disabled = false;
        }
    })
        
})
