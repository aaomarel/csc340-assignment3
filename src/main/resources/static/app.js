// API Base URL
const API_BASE_URL = '/api/marineanimals';

// State to track if we're editing
let isEditing = false;
let currentEditId = null;

// Load all animals when page loads
document.addEventListener('DOMContentLoaded', () => {
    loadAllAnimals();
    setupFormSubmit();
});

/**
 * Setup form submit handler
 */
function setupFormSubmit() {
    const form = document.getElementById('animalForm');
    form.addEventListener('submit', (e) => {
        e.preventDefault();
        if (isEditing) {
            updateAnimal();
        } else {
            addAnimal();
        }
    });
}

/**
 * GET - Fetch all marine animals
 */
async function loadAllAnimals() {
    console.log('Fetching all marine animals from:', API_BASE_URL);
    
    const container = document.getElementById('animalsContainer');
    container.innerHTML = '<p class="loading">Loading animals...</p>';

    try {
        const response = await fetch(API_BASE_URL);
        console.log('Response status:', response.status);
        
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        
        const animals = await response.json();
        console.log('Fetched animals:', animals);
        
        displayAnimals(animals);
    } catch (error) {
        console.error('Error fetching animals:', error);
        container.innerHTML = `<p class="error">Error loading animals: ${error.message}</p>`;
    }
}

/**
 * GET - Search animals by name
 */
async function searchAnimals() {
    const searchTerm = document.getElementById('searchInput').value.trim();
    
    if (!searchTerm) {
        loadAllAnimals();
        return;
    }

    console.log('Searching for animals with name:', searchTerm);
    
    const container = document.getElementById('animalsContainer');
    container.innerHTML = '<p class="loading">Searching...</p>';

    try {
        const url = `${API_BASE_URL}/search?name=${encodeURIComponent(searchTerm)}`;
        console.log('Search URL:', url);
        
        const response = await fetch(url);
        console.log('Search response status:', response.status);
        
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        
        const animals = await response.json();
        console.log('Search results:', animals);
        
        displayAnimals(animals);
    } catch (error) {
        console.error('Error searching animals:', error);
        container.innerHTML = `<p class="error">Error searching animals: ${error.message}</p>`;
    }
}

/**
 * POST - Add a new marine animal
 */
async function addAnimal() {
    const animalData = {
        name: document.getElementById('name').value,
        species: document.getElementById('species').value,
        habitat: document.getElementById('habitat').value,
        description: document.getElementById('description').value
    };

    console.log('Adding new animal:', animalData);

    try {
        const response = await fetch(API_BASE_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(animalData)
        });

        console.log('POST response status:', response.status);

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const newAnimal = await response.json();
        console.log('Animal added successfully:', newAnimal);

        // Reset form and reload animals
        resetForm();
        loadAllAnimals();
        
        alert('Animal added successfully!');
    } catch (error) {
        console.error('Error adding animal:', error);
        alert(`Error adding animal: ${error.message}`);
    }
}

/**
 * PUT - Update an existing marine animal
 */
async function updateAnimal() {
    const animalData = {
        name: document.getElementById('name').value,
        species: document.getElementById('species').value,
        habitat: document.getElementById('habitat').value,
        description: document.getElementById('description').value
    };

    console.log(`Updating animal ID ${currentEditId}:`, animalData);

    try {
        const url = `${API_BASE_URL}/${currentEditId}`;
        console.log('PUT URL:', url);
        
        const response = await fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(animalData)
        });

        console.log('PUT response status:', response.status);

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const updatedAnimal = await response.json();
        console.log('Animal updated successfully:', updatedAnimal);

        // Reset form and reload animals
        resetForm();
        loadAllAnimals();
        
        alert('Animal updated successfully!');
    } catch (error) {
        console.error('Error updating animal:', error);
        alert(`Error updating animal: ${error.message}`);
    }
}

/**
 * DELETE - Delete a marine animal by ID
 */
async function deleteAnimal(id) {
    if (!confirm('Are you sure you want to delete this animal?')) {
        return;
    }

    console.log('Deleting animal ID:', id);

    try {
        const url = `${API_BASE_URL}/${id}`;
        console.log('DELETE URL:', url);
        
        const response = await fetch(url, {
            method: 'DELETE'
        });

        console.log('DELETE response status:', response.status);

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        console.log('Animal deleted successfully');

        // Reload animals
        loadAllAnimals();
        
        alert('Animal deleted successfully!');
    } catch (error) {
        console.error('Error deleting animal:', error);
        alert(`Error deleting animal: ${error.message}`);
    }
}

/**
 * GET - Fetch a single animal by ID and populate form for editing
 */
async function editAnimal(id) {
    console.log('Fetching animal for edit, ID:', id);

    try {
        const url = `${API_BASE_URL}/${id}`;
        console.log('GET URL:', url);
        
        const response = await fetch(url);
        console.log('GET response status:', response.status);

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const animal = await response.json();
        console.log('Fetched animal for editing:', animal);

        // Populate form
        document.getElementById('animalId').value = animal.animalId;
        document.getElementById('name').value = animal.name;
        document.getElementById('species').value = animal.species;
        document.getElementById('habitat').value = animal.habitat;
        document.getElementById('description').value = animal.description;

        // Update UI
        isEditing = true;
        currentEditId = id;
        document.getElementById('formTitle').textContent = 'Edit Marine Animal';
        document.getElementById('submitBtn').textContent = 'Update Animal';
        document.getElementById('cancelBtn').style.display = 'inline-block';

        // Scroll to form
        document.getElementById('animalForm').scrollIntoView({ behavior: 'smooth' });
    } catch (error) {
        console.error('Error fetching animal for edit:', error);
        alert(`Error loading animal: ${error.message}`);
    }
}

/**
 * Reset form to initial state
 */
function resetForm() {
    document.getElementById('animalForm').reset();
    document.getElementById('animalId').value = '';
    
    isEditing = false;
    currentEditId = null;
    
    document.getElementById('formTitle').textContent = 'Add New Marine Animal';
    document.getElementById('submitBtn').textContent = 'Add Animal';
    document.getElementById('cancelBtn').style.display = 'none';
}

/**
 * Display animals in the container
 */
function displayAnimals(animals) {
    const container = document.getElementById('animalsContainer');

    if (!animals || animals.length === 0) {
        container.innerHTML = '<p class="no-results">No animals found.</p>';
        return;
    }

    container.innerHTML = animals.map(animal => `
        <div class="animal-card">
            <h3>${escapeHtml(animal.name)}</h3>
            <p><span class="label">ID:</span> ${animal.animalId}</p>
            <p><span class="label">Species:</span> ${escapeHtml(animal.species)}</p>
            <p><span class="label">Habitat:</span> ${escapeHtml(animal.habitat)}</p>
            <p><span class="label">Description:</span> ${escapeHtml(animal.description)}</p>
            <div class="animal-actions">
                <button class="edit-btn" onclick="editAnimal(${animal.animalId})">Edit</button>
                <button class="delete-btn" onclick="deleteAnimal(${animal.animalId})">Delete</button>
            </div>
        </div>
    `).join('');
}

/**
 * Escape HTML to prevent XSS
 */
function escapeHtml(text) {
    const div = document.createElement('div');
    div.textContent = text;
    return div.innerHTML;
}

// Allow search on Enter key
document.addEventListener('DOMContentLoaded', () => {
    const searchInput = document.getElementById('searchInput');
    if (searchInput) {
        searchInput.addEventListener('keypress', (e) => {
            if (e.key === 'Enter') {
                searchAnimals();
            }
        });
    }
});
