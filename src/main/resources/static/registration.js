const usersUrl = 'http://localhost:8090/api/registration';


const userNameContainer = document.querySelector(".navbar__user");
const userRoleContainer = document.querySelector(".navbar__role");
const button = document.querySelector(".btn-new");
const form = document.querySelector(".form-registration");

console.log(userNameContainer, userRoleContainer);


const getUsersInfo = (usersUrl) => {
    fetch(usersUrl)
        .then(response => response.json())
        .then(user => {
            console.log(user[0].body);
            addDataInHeader(user[0].body);
        });
}

getUsersInfo(usersUrl);

const onSuccess = () => {
    console.log("Form sent.")
};

const showAlert = () => {
    console.log("Не удалось отправить форму. Попробуйте ещё раз");
};

const sendData = (onSuccess, showAlert, data) => {
    fetch(
        usersUrl,
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data),
        },
    )
        .then((response) => {
            if (response.ok) {
                onSuccess();
            } else {
                showAlert();
            }
        })
        .catch(() => {
            showAlert();
        });
};

const setUserFormSubmit = (onSuccess, showAlert) => {
    form.addEventListener('submit', (evt) => {
        evt.preventDefault();
        console.log(new FormData(evt.target));
        console.log(evt.target);

        // let editUserRoles = [];
        // for (let i = 0; i < form.roles.options.length; i++) {
        //     if (form.roles.options[i].selected) editUserRoles.push({
        //         id : form.roles.options[i].value,
        //         role : "ROLE_" + form.roles.options[i].text
        //     })
        // }


        const userData = {
            name: document.querySelector('#username').value,
            surname: document.querySelector('#surname').value,
            username: document.querySelector('#username').value,
            email: document.querySelector('#email').value,
            password: document.querySelector('#password').value,
            roles: ["ROLE_USER"],
        };

        sendData(
            () => onSuccess(),
            () => showAlert(),
            userData,
        );
    });
};

setUserFormSubmit(onSuccess, showAlert)


function addDataInHeader(user) {

    const role = `
    <li class="nav-item text-white item">
        ${user.role.map(role => createRoleItem(role)).join(' ')}
    </li>`;
    userNameContainer.innerText = user.name;
    userRoleContainer.innerHTML = role;
}

const createRoleItem = (role) => (
    `<span style="margin-right: 5px">${role.name.replace('ROLE_', '')}</span>`
);