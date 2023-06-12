let login_form = document.getElementById('login-validation');

login_form.addEventListener('submit', async (e) => {
    e.preventDefault();
    e.stopPropagation();

    if (login_form.checkValidity() === true) {
        let form_data = new FormData();
        form_data.append('username', document.getElementById('inputEmail').value);
        form_data.append('password', document.getElementById("inputPassword").value);

        let response = await fetch('api/books/login', {
            method: 'POST',
            body: form_data
        });

        let result = await response.text();

        if (result === 'admin') {
            sessionStorage.setItem('login_type','admin')
            let response =  await fetch('api/books/loadbasemodel',{
                method:'POST',

            });
            let result = await response.text();

            location.href = "admin.html"

        } else if(result === 'user') {

            sessionStorage.setItem('login_type','user')
            location.href = "user.html"
        }
        else
            document.getElementById("login-alert").style.display = "block";
    }
});