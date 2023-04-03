const trainersContainer = document.getElementById("trainers-container");

fetch("http://localhost:8080/trainers")
    .then(response => response.json())
    .then(trainers => {
        trainers.forEach(trainer => {
            const trainerDiv = document.createElement("div");
            trainerDiv.className= "trainer-container";
            trainerDiv.innerHTML = `
                <h3>${trainer.username}</h3>
                <p>Email: ${trainer.email}</p>
                <p>Bio: ${trainer.bio}</p>
            `;
            trainersContainer.appendChild(trainerDiv);
        });
    })
    .catch(error => {
        console.error("Error fetching trainers:", error);
    });
