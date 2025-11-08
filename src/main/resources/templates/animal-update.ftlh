<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ocean Life Gallery - Update Animal</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
    <link href="/styles.css" rel="stylesheet">
</head>
<body>
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">
            <a class="navbar-brand" href="/animals">
                <i class="fas fa-fish"></i> Ocean Life Gallery
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="/animals">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="#">Edit Animal</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Form Section -->
    <main class="container py-5">
        <div class="row justify-content-center">
            <div class="col-lg-8">
                <div class="card shadow">
                    <div class="card-header bg-warning text-dark">
                        <h2 class="mb-0">
                            <i class="fas fa-edit"></i> Update Marine Animal
                        </h2>
                        <p class="mb-0 mt-2">Edit the details for ${animal.name}</p>
                    </div>
                    <div class="card-body p-4">
                        <form action="/animals/update" method="post">
                            <!-- Hidden ID field - required for update -->
                            <input type="hidden" name="animalId" value="${animal.animalId}">
                            
                            <div class="mb-3">
                                <label class="form-label">
                                    <i class="fas fa-hashtag text-primary"></i> Animal ID (Read-only)
                                </label>
                                <input type="text" class="form-control" value="${animal.animalId}" disabled>
                            </div>

                            <!-- Basic Information -->
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="name" class="form-label">
                                        <i class="fas fa-tag text-primary"></i> Common Name *
                                    </label>
                                    <input type="text" class="form-control" id="name" name="name" 
                                           value="${animal.name}" required>
                                </div>

                                <div class="col-md-6 mb-3">
                                    <label for="species" class="form-label">
                                        <i class="fas fa-microscope text-primary"></i> Scientific Name *
                                    </label>
                                    <input type="text" class="form-control" id="species" name="species" 
                                           value="${animal.species}" required>
                                </div>
                            </div>

                            <!-- Habitat & Description -->
                            <h5 class="mt-4 mb-3">
                                <i class="fas fa-globe text-primary"></i> Habitat & Details
                            </h5>
                            
                            <div class="mb-3">
                                <label for="habitat" class="form-label">
                                    Habitat * 
                                    <small class="text-muted">(<span id="habitatCount">0</span>/255 characters)</small>
                                </label>
                                <textarea class="form-control" id="habitat" name="habitat" rows="3" required 
                                          maxlength="255" oninput="updateCharCount('habitat', 'habitatCount', 255)">${animal.habitat}</textarea>
                            </div>

                            <div class="mb-3">
                                <label for="description" class="form-label">
                                    <i class="fas fa-info-circle text-primary"></i> Description *
                                    <small class="text-muted">(<span id="descriptionCount">0</span>/255 characters)</small>
                                </label>
                                <textarea class="form-control" id="description" name="description" rows="4" required 
                                          maxlength="255" oninput="updateCharCount('description', 'descriptionCount', 255)">${animal.description}</textarea>
                            </div>

                            <!-- Submit Buttons -->
                            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                <a href="/animals/${animal.animalId}" class="btn btn-secondary me-md-2">
                                    <i class="fas fa-times"></i> Cancel
                                </a>
                                <button type="submit" class="btn btn-warning">
                                    <i class="fas fa-save"></i> Update Animal Information
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <!-- Footer -->
    <footer class="bg-dark text-white text-center py-4">
        <div class="container">
            <p>&copy; 2025 Ocean Life Gallery. All rights reserved.</p>
            <p>Dedicated to marine conservation and education.</p>
        </div>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function updateCharCount(textareaId, counterId, maxLength) {
            const textarea = document.getElementById(textareaId);
            const counter = document.getElementById(counterId);
            const currentLength = textarea.value.length;
            counter.textContent = currentLength;
            
            // Change color based on usage
            if (currentLength >= maxLength) {
                counter.style.color = 'red';
                counter.style.fontWeight = 'bold';
            } else if (currentLength >= maxLength * 0.9) {
                counter.style.color = 'orange';
            } else {
                counter.style.color = '#6c757d';
            }
        }
        
        // Initialize counters on page load
        window.onload = function() {
            updateCharCount('habitat', 'habitatCount', 255);
            updateCharCount('description', 'descriptionCount', 255);
        };
    </script>
</body>
</html>
