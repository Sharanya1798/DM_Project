window.onload= checkWhetherLoggedIn;

async function checkWhetherLoggedIn() {
    if(!sessionStorage.getItem("login_type")) {
        location.href="signin.html";
    } else if(sessionStorage.getItem("login_type") === 'user') {
        location.href="user.html"
    } else
        d1();
}

async function d1(){
    let response = await fetch('api/books/d1', {
        method: 'POST',
    });
    let result = await response.json();
    let i,n=result.length,h,drop=document.getElementById('d1');
    for(i=0;i<n;i++){
        h=document.createElement('option');
        h.innerHTML=result[i];
        drop.appendChild(h);
    }
    let h1,drop1=document.getElementById('d5');

    for(i=0;i<n;i++){
        h1=document.createElement('option');
        h1.setAttribute('value',result[i]);
        h1.innerHTML=result[i];
        drop1.appendChild(h1);
    }
}

document.getElementById('FileUpload').onclick= async (e) => {
    e.preventDefault();
    e.stopPropagation();
    let ont = document.getElementById("file").files[0];
    let formData = new FormData();

    formData.append("ont", ont);
    let response = await fetch('api/books/d2', {
        method: 'POST',
        body: formData,
    });
    let result = await response.json();
    console.log("done");
    let i,n=result.length,h,drop=document.getElementById('d2');;
    for(i=0;i<n;i++){
        h=document.createElement('option');
        h.innerHTML=result[i];
        drop.appendChild(h);
    }
    let h1,drop1=document.getElementById('d3');;
    for(i=0;i<n;i++){
        h1=document.createElement('option');
        h1.innerHTML=result[i];
        h1.setAttribute("value",result[i]);
        drop1.appendChild(h1);
    }


}

async  function getproperties()
{
    var value = document.getElementById("d3").value;
    console.log(value);

    let formData = new FormData();
    formData.append('className',value );

    let response = await fetch('api/books/objectproperties', {
        method: 'POST',
        body:formData
    });

    let result = await response.json();
    console.log(result);

    let i,n=result.length,h,drop=document.getElementById('d4');
    let drop1=document.getElementById('d61');
    let  drop2=document.getElementById('d62');
    let drop3=document.getElementById('d63');
    let drop4=document.getElementById('d64');
    drop.innerText = null ;
    for(i=0;i<n;i++){
        h=document.createElement('option');
        h.innerHTML=result[i];
        h.setAttribute("value",result[i]);
        drop.appendChild(h);
    }
    drop1.innerText = null ;
    for(i=0;i<n;i++){
        h=document.createElement('option');
        h.innerHTML=result[i];
        h.setAttribute("value",result[i]);
        drop1.appendChild(h);
    }
    drop2.innerText = null ;
    for(i=0;i<n;i++){
        h=document.createElement('option');
        h.innerHTML=result[i];
        h.setAttribute("value",result[i]);
        drop2.appendChild(h);
    }
    drop3.innerText = null ;
    for(i=0;i<n;i++){
        h=document.createElement('option');
        h.innerHTML=result[i];
        h.setAttribute("value",result[i]);
        drop3.appendChild(h);
    }
    drop4.innerText = null ;
    for(i=0;i<n;i++){
        h=document.createElement('option');
        h.innerHTML=result[i];
        h.setAttribute("value",result[i]);
        drop4.appendChild(h);
    }







}




async  function baseproperties()
{
    var value = document.getElementById("d5").value;
    console.log(value);

    let formData = new FormData();
    formData.append('className',value );

    let response = await fetch('api/books/baseobjectproperties', {
        method: 'POST',
        body:formData
    });

    let result = await response.json();
    console.log(result);

    let i,n=result.length,h,drop=document.getElementById('d6');
    drop.innerText = null ;
    for(i=0;i<n;i++){
        h=document.createElement('option');
        h.setAttribute('value',result[i]);
        h.innerHTML=result[i];
        drop.appendChild(h);
    }
}



let mappingform = document.getElementById('mappingform');

mappingform.onsubmit = async (e) => {
    e.preventDefault();
    e.stopPropagation();

        let FORMDATA = new FormData();

        var x =  document.getElementById("d61").value;

        console.log(x);

        FORMDATA.append('bookname',document.getElementById("d61").value);
        FORMDATA.append('author', document.getElementById("d63").value);
        FORMDATA.append('edition',document.getElementById("d64").value);
        FORMDATA.append('bookid',document.getElementById("d62").value);
        FORMDATA.append('class', document.getElementById("d4").value);



        let response = await fetch('api/books/mapping', {
            method: 'POST',
            body: FORMDATA
        });
        let result = await response.json();
        // // console.log("Result is - ");
        // console.log(result);
        // var table = document.getElementById("data");
        // table.innerHTML="";
        // var tr="";
        // result.forEach(x=>{
        //     tr+='<tr>';
        //     tr+='<td>'+x[0]+'</td>'+'<td>'+x[1]+'</td>'+'<td>'+x[2]+'</td>'
        //     tr+='</tr>'
        //
        // })
        // table.innerHTML+=tr;
    console.log(FORMDATA);
};


