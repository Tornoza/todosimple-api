const url ="http://localhost:8080/task/user/1";

function hideLoader(){
    document.getElementById("loading").style.display = "nome"
}

function show(tasks){

    let tab = `<thead>
            <th scoope="col">#</th>
            <th scoope="col">Description<th>
            <th scoope="col">Username<th>
            <th scoope="col">User Id<th
        </thead>`;

    for (const task of  tasks){
        tab += `
            <tr>
                <td scope="row">${task.id}</td>
                <td> ${task.description}</td>
                <td> ${task.user.username}</td> 
                <td> ${task.user.id}</td>   
        `

    }

    document.getElementById("tasks").innerHTML = tab;
}
async function getAPI(uri){
    const response = await fetch(url, {method: "GET"});

    var date = await response.json();
    console.log(data);
    if(response)
        hideLoader()
    show(data)
}

getAPI(url);