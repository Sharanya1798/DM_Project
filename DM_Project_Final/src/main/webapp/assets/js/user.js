window.onload= checkWhetherLoggedIn;

async function checkWhetherLoggedIn() {
    if(!sessionStorage.getItem("login_type")) {
        location.href="signin.html";


    } else if(sessionStorage.getItem("login_type") === 'admin') {
        location.href = "admin.html"
    }
}


let selection_form = document.getElementById('selection-form');

selection_form.onsubmit = async (e) => {
    e.preventDefault();
    e.stopPropagation();
    if (selection_form.checkValidity() === true) {
        let form_data = new FormData();
        form_data.append('skill', document.getElementById('skill').value);
        form_data.append('location', document.getElementById('location').value);
        form_data.append('experience', document.getElementById('experience').value);

        let response = await fetch('api/books/search', {
            method: 'POST',
            body: form_data
        });
        let result = await response.json();
        // console.log("Result is - ");
        console.log(result);
        var table = document.getElementById("data");
        table.innerHTML= null;
        var tr="";
        result.forEach(x=>{
            tr+='<tr>';
            tr+='<td>'+x[0]+'</td>'+'<td>'+x[1]+'</td>'+'<td>'+x[2]+'</td>'+'<td>'+x[3]+'</td>'
            tr+='</tr>'

        })
        table.innerHTML+=tr;


    } else {
        selection_form.classList.add('was-validated');
    }
};


