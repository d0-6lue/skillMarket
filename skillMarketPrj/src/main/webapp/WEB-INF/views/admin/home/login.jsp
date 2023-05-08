<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <style>
        body {background-color: #2f3334;}

        #wrap{
            position: absolute;
            width: 650px;
            height: 500px;

            background-color: #273F6D;
            box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 5px 0px, rgba(0, 0, 0, 0.1) 0px 0px 1px 0px;
            box-sizing: border-box;
            border-radius: 10px;

            top:50%;
            left:50%;
            transform:translate(-50%,-50%);
            
            display: grid;
            grid-template-rows: 1fr 2fr;

            
        }

        #logo_area {
            /* position: relative; */
            width: 252;
            height: 60%;
            margin: auto;
            background: #FDFDFD;
            box-shadow: 1px 2px 0px rgba(0, 0, 0, 0.25);
            border-radius: 10px;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        img {
            height: 90%;
            
            
            object-fit: cover;
            transform: translate(50,50);

        }

        #login_area {
            width: 50%;
            height: 90%;
            margin: 0 auto;

            background: #FDFDFD;
            box-shadow: 1px 2px 0px rgba(0, 0, 0, 0.25);
            border-radius: 10px;

            display: grid;
            grid-template-rows: 1fr 2fr;
            text-align: center;
            align-items: center;
        }

        div {margin: auto;}

        h1 {
            font-weight: 700;
            font-size: 30px;
            color: #3C3C3C;
        }
        
        form {
            width: 100%;
            height: 100%;
        }

        

        input {
            width: 60%;
            height: 20%;
            border: 2px soild #3C3C3C;
            border-radius: 10px;
            margin-bottom: 5px;
        }

    </style>
</head>
<body>
    
    <div id="wrap">
        <div id="logo_area">
            <img src="${root}/static/svg/관리자 페이지 로고.svg" alt="로고">
        </div>

        <div id="login_area">
            <h1>관리자 로그인</h1>
            <form action="">
                <div>
                    <input type="text">
                    <input type="password">
                </div>
                <input type="submit">
            </form>
            

        </div>
    </div>
    

</body>
</html>

