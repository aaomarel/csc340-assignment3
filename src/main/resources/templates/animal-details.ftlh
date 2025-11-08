<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ocean Life Gallery - ${animal.name} Details</title>
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
                        <a class="nav-link active" href="#">Animal Details</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/animals/new">Add Animal</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Animal Details Section -->
    <main class="container py-5">
        <div class="row justify-content-center">
            <div class="col-lg-8">
                <h1 class="mb-4 text-center">${animal.name}</h1>
                <h5 class="text-muted mb-4 text-center">${animal.species}</h5>
                
                <div class="card mb-4">
                    <div class="card-body">
                        <h6 class="card-title">Quick Facts</h6>
                        <ul class="list-unstyled">
                            <li><strong>Animal ID:</strong> ${animal.animalId}</li>
                            <li><strong>Scientific Name:</strong> ${animal.species}</li>
                            <li><strong>Habitat:</strong> ${animal.habitat}</li>
                        </ul>
                    </div>
                </div>

                <div class="card mb-4">
                    <div class="card-body">
                        <h6 class="card-title">About the ${animal.name}</h6>
                        <p class="mb-0">${animal.description}</p>
                    </div>
                </div>

                <div class="d-flex gap-2 justify-content-center mb-4">
                    <a href="/animals/update/${animal.animalId}" class="btn btn-warning">
                        <i class="fas fa-edit"></i> Edit Animal
                    </a>
                    <a href="/animals/delete/${animal.animalId}" class="btn btn-danger" 
                       onclick="return confirm('Are you sure you want to delete ${animal.name}?')">
                        <i class="fas fa-trash"></i> Delete Animal
                    </a>
                </div>
            </div>
        </div>

        <div class="row mt-4">
            <div class="col-12 text-center">
                <a href="/animals" class="btn btn-primary btn-lg">
                    <i class="fas fa-arrow-left"></i> Back to Gallery
                </a>
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
</body>
</html>
