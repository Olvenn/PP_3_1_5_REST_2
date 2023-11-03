const usersUrl = 'http://localhost:8090/api/users';

const userContainer = document.querySelector(".list-users");
const userNameContainer = document.querySelector(".navbar__user");
const userRoleContainer = document.querySelector(".navbar__role");
const userDeleteBtn = document.querySelector(".btn-delete");

fetch('http://localhost:8090/api/users/1')
    .then(response => response.json())
    .then(user => {
        console.log(user);
    });

const getUsersTable = (usersUrl) => {
    fetch(usersUrl)
        .then(response => response.json())
        .then(user => {
            // console.log(user[1].body);
            addDataInHeader(user[0].body);
            addUsersList(user[1].body)
            // createUserItem(user[1].body[1])
            deleteUsers();
            // deleteUser();
            // addRollList(user[1].body)
            getEditButtons();
        });
}

getUsersTable(usersUrl);

function addUsersList(users) {

    const usersList = `
    <tbody class="content-users__main">
        ${users.map(user => createUserItem(user))}
    </tbody>`;
    userContainer.insertAdjacentHTML("beforeend", usersList);
}

function getEditButtons() {
    const buttons = document.querySelectorAll('.form-edit');
    console.log("getEditButtons", buttons);

    buttons.forEach(button => {
        button.addEventListener('submit', (evt) => {
            evt.preventDefault();
            console.log("edit", button);

            editUser(evt.target);
        });
    });
}

function editUser(dataAll) {
    const id = dataAll.id.value;

    let editUserRoles = [];
    for (let i = 0; i < dataAll.roles.options.length; i++) {
        if (dataAll.roles.options[i].selected) editUserRoles.push({
            id : dataAll.roles.options[i].value,
            role : "ROLE_" + dataAll.roles.options[i].text
        })
    }

    fetch("http://localhost:8080/api/users/" + id, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: dataAll.username.value,
            surname: dataAll.surname.value,
            username: dataAll.username.value,
            email: dataAll.email.value,
            password: dataAll.password.value,
            roles: editUserRoles,
        })
    }).then(() => {
        addUsersList(dataAll)
    })
}

function deleteUser(id) {
    console.log("userDeleteBtn", userDeleteBtn);
    fetch("http://localhost:8090/api/users/" + id, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(() => {
        console.log("deleteUser");
    })
};

function deleteUsers() {
    const buttons = document.querySelectorAll('.form-delete');
    console.log(buttons);

    buttons.forEach(button => {
        button.addEventListener('submit', (evt) => {
            evt.preventDefault();
            // console.log("delete", button);
            // console.log("evt", evt.target.id.value);

            deleteUser(evt.target.id.value);
        });
    });
}

const createUserItem = (userNext) => {
    const userList = ` 
        <tr class="content-users__main border-top bg-light">
            <td class="bg-light">${userNext.id}</td>
            <td class="bg-light">${userNext.username}</td>
            <td class="bg-light">${userNext.name}</td>
            <td class="bg-light">${userNext.surname}</td>
            <td class="bg-light">${userNext.email}</td>
            <td class="bg-light">${userNext.role.map(role => createRoleItem(role)).join(' ')}</td>
            <td class="bg-light">
              
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#updateModal${userNext.id}">
                  Edit
                </button>
        
        <!-- Модальное окно -->
                <div class="modal fade" id="updateModal${userNext.id}" tabindex="-1" aria-labelledby="exampleModalLabel"
                  aria-hidden="true">
                  <div class="modal-dialog">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Заголовок модального окна</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
                      </div>
                      <div class="modal-body">
                        <form class="form-edit form-group mx-auto col-lg-6 text-center">
                          <div class="form-group">
                            <label for="ID-del">ID</label>
                            <input class="form-control" type="text" name="id" value="${userNext.id}" id="ID" disabled>
                          </div>
                          <div class="form-group">
                            <label for="username-del">Login:</label>
                            <input class="form-control" type="text" name="username" value="${userNext.username}" id="username">
                          </div>
                          <div class="form-group">
                            <label for="password-del">Password: </label>
                            <input class="form-control" type="password" name="password" th:value="${userNext.password}" id="password" />
                          </div>
                          <div class="form-group">
                            <label for="name-del">Name: </label>
                            <input class="form-control" type="text" name="name" value="${userNext.name}" id="name">
                          </div>
                          <div class="form-group">
                            <label for="surname-del">Surname: </label>
                            <input class="form-control" type="text" name="surname" value="${userNext.surname}" id="surname" />
                          </div>
                          <div class="form-group">
                            <label for="email-del">E-mail: </label>
                            <input class="form-control" type="email" name="email" value="${userNext.email}" id="email" />
                          </div>
                          <div class="form-group mb-3">
                                <label for="role">Role</label>
                                <div>
                                    <select multiple class="custom-select" name="roles" size="2"
                                            id="role" required>
                                        <option>
                                            ADMIN
                                        </option>
                                        <option>
                                            USER
                                        </option>
                                    </select>
                                </div>
                            </div>
                          <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                Close
                            </button>
                            <button type="submit" class="btn btn-primary btn-edit">
                                Edit
                            </button>
                          </div>
                        </form>
                      </div>
                    </div>
                  </div>
                </div>
            </td>
          
           <td class="bg-light">
           <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal${userNext.id}">
              Delete
           </button>
    
    <!-- Модальное окно -->
            <div class="modal fade" id="deleteModal${userNext.id}" tabindex="-1" aria-labelledby="exampleModalLabel"
              aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Заголовок модального окна</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
                  </div>
                  <div class="modal-body">
                    <form class="form-delete form-group mx-auto col-lg-6 text-center">
                      <div class="form-group">
                        <label for="ID-del">ID</label>
                        <input class="form-control" type="text" name="id" value="${userNext.id}" id="ID-del" disabled>
                      </div>
                      <div class="form-group">
                        <label for="username-del">Login:</label>
                        <input class="form-control" type="text" name="username" value="${userNext.username}" id="username-del" disabled>
                      </div>
                      <div class="form-group">
                        <label for="password-del">Password: </label>
                        <input class="form-control" type="password" name="password" value="${userNext.password}" id="password-del" disabled/>
                      </div>
                      <div class="form-group">
                        <label for="name-del">Name: </label>
                        <input class="form-control" type="text" name="name" value="${userNext.name}" id="name-del" disabled>
                      </div>
                      <div class="form-group">
                        <label for="surname-del">Surname: </label>
                        <input class="form-control" type="text" name="surname" value="${userNext.surname}" id="surname-del" disabled/>
                      </div>
                      <div class="form-group">
                        <label for="email-del">E-mail: </label>
                        <input class="form-control" type="email" name="email" value="${userNext.email}" id="email-del" disabled/>
                      </div>
                       <div class="form-group mb-3">
                                <label for="role">Role</label>
                                <div>
                                    <select multiple class="custom-select" name="roles" size="2"
                                            id="role" required>
                                        <option>
                                            ADMIN
                                        </option>
                                        <option>
                                            USER
                                        </option>
                                    </select>
                                </div>
                            </div>
                      <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                        </button>
                        <button type="submit" class="btn btn-primary btn-delete" data-bs-target="${userNext.id}">
                            Delete
                        </button>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </td>
      </tr>
  `;
    return userList;
};

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
