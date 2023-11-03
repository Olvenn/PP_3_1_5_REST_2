const userUrl = 'http://localhost:8090/api/user';

const userContainer = document.querySelector(".user-container");
const userNameContainer = document.querySelector(".navbar__user");
const userRoleContainer = document.querySelector(".navbar__role");

fetch(userUrl)
    .then(response => response.json())
    .then(user => {
        createUserTemplate(user);
        addDataInHeader(user);
    });

function createUserTemplate(user) {

    const result =
        `<tr class="border-top bg-light"">
            <td class="bg-light">${user.id}</td>
            <td class="bg-light">${user.name}</td>
            <td class="bg-light">${user.surname}</td>
            <td class="bg-light">${user.email}</td>
            <td class="d-flex bg-light">${user.role.map(role => createRoleItem(role)).join(' ')}</td>
         </tr>`
    userContainer.innerHTML = result;
}


const createUserItem = (user) => (
    `<tr class="border-top bg-light"">
            <td class="bg-light">${user.id}</td>
            <td class="bg-light">${user.name}</td>
            <td class="bg-light">${user.surname}</td>
            <td class="bg-light">${user.email}</td>
            <td class="d-flex bg-light">${user.role.map(role => createRoleItem(role)).join(' ')}</td>
         </tr>`
);



const createRoleItem = (role) => (
    `<span style="margin-right: 5px">${role.name.replace('ROLE_', '')}</span>`
);

function addDataInHeader(user) {

    const role = `
    <li className="nav-item text-white item">
        ${user.role.map(role =>  createRoleItem(role)).join(' ')}
    </li>`;
    userNameContainer.innerText = user.name;
    userRoleContainer.innerHTML = role;
}
