// Tournaments JS

const sampleData = { // This might be replaced via csv or database or something in the future
    "Tournament A": {
        organizers: ["Alice"],
        description: ["This is the description of tournament A"],
        coaches: ["Coach X", "Coach Y"],
        students: ["Student 1", "Student 2", "Student 3"]
    },
    "Tournament B": {
        organizers: ["Charlie"],
        description: ["This is the description of tournament B"],
        coaches: ["Coach Z"],
        students: ["Student 4", "Student 5"]
    }
};

function showDetails(tournamentName) { // Display all info of a tournament when the column is clicked on
    const data = sampleData[tournamentName];
    if (data) {
        document.getElementById("modalTitle").innerText = tournamentName;
        document.getElementById("organizersList").innerText = data.organizers.join(", ");
        document.getElementById("descriptionList").innerText = data.description.join(", ")
        document.getElementById("coachesList").innerText = data.coaches.join(", ");
        document.getElementById("studentsList").innerText = data.students.join(", ");
        document.getElementById("overlay").style.display = "block";
        document.getElementById("detailsModal").style.display = "block";
    }
}

function closeModal() {
    document.getElementById("overlay").style.display = "none";
    document.getElementById("detailsModal").style.display = "none";
}