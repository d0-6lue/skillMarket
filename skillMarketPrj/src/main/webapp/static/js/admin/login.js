const adminId = document.querySelector("input[name=adminId]");
const adminPwd = document.querySelector("input[name=adminPwd]");
const submit = document.querySelector(".submit");
const errorText = document.querySelector(".error-text");

submit.disabled = true;

adminId.addEventListener("input", checkInputFields);
adminPwd.addEventListener("input", checkInputFields);

submit.addEventListener("click", (event) => {
  event.preventDefault();

  const data = {
    adminId: adminId.value,
    adminPwd: adminPwd.value
  };

  fetch("/skillmarket/admin/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(data)
  })
    .then(response => response.json())
    .then(result => {
      if (result.success) {
        // 로그인 성공
        errorText.textContent = "";
        errorText.style.color = "#3C3C3C";
        submit.disabled = false;
        // 페이지 전환
        window.location.href = "/skillmarket/admin/home";
      } else {
        // 로그인 실패
        errorText.style.color = "#F36969";
        errorText.textContent = "아이디/비밀번호를 확인해주세요";
        submit.disabled = true;
        
        vibration();

      }
    })
    .catch(error => {
      console.error("Error:", error);
    });
});

function checkInputFields() {
  let isEmpty = false;

  if (adminId.value === "") {
    adminId.style.borderColor = "#F36969";
    adminId.style.borderWidth = "3px";
    isEmpty = true;
  } else {
    adminId.style.borderColor = "#585858";
    adminId.style.borderWidth = "0";
  }

  if (adminPwd.value === "") {
    adminPwd.style.borderColor = "#F36969";
    adminPwd.style.borderWidth = "3px";
    isEmpty = true;
  } else {
    adminPwd.style.borderColor = "#585858";
    adminPwd.style.borderWidth = "0";
  }

  submit.disabled = isEmpty;
}


/*-------------*/
//에러 메세지 진동
function vibration() {
    errorText.classList.add("vibration");

    setTimeout(function() {
        errorText.classList.remove("vibration");
    }, 200);
}