window.onload = function(){

    // span 0 이면 숫자 잘안보이게
    const sapns = document.querySelectorAll("span");
        
    sapns.forEach(span => {
        const value = parseInt(span.textContent);
        
        if (value === 0) {
            span.style.color = '#dddddd';
        }
        
    })


    
        

}
