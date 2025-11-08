<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ocean Life Gallery - Home</title>
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
                        <a class="nav-link active" href="/animals">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/animals/new">Add Animal</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Header -->
    <header class="hero-section">
        <div class="container text-center text-white">
            <h1 class="display-4 mb-3">Ocean Life Gallery</h1>
            <p class="lead">Discover the wonders of marine life from around the world</p>
        </div>
    </header>

    <!-- Search Bar -->
    <section class="search-section py-4">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <form action="/animals/search" method="get">
                        <div class="input-group">
                            <input type="text" class="form-control" name="name" id="searchInput" 
                                   placeholder="Search animals..." value="${searchTerm!''}">
                            <button class="btn btn-primary" type="submit">
                                <i class="fas fa-search"></i>
                            </button>
                        </div>
                    </form>
                    <#if searchTerm??>
                        <div class="text-center mt-2">
                            <a href="/animals" class="btn btn-sm btn-outline-secondary">Clear Search</a>
                        </div>
                    </#if>
                </div>
            </div>
        </div>
    </section>

    <!-- Animal Gallery -->
    <main class="container py-5">
        <h2 class="text-center mb-5">Our Marine Animals</h2>
        
        <#if animalList?has_content>
            <div class="row" id="animalGallery">
                <#list animalList as animal>
                    <div class="col-lg-4 col-md-6 mb-4 animal-card fade-in">
                        <div class="card h-100 shadow">
                            <div class="card-body">
                                <h5 class="card-title">${animal.name}</h5>
                                <p class="card-text">
                                    <strong>Species:</strong> ${animal.species}<br>
                                    <strong>Habitat:</strong> ${animal.habitat}<br>
                                    <strong>Description:</strong> ${animal.description?truncate(100, '...')}
                                </p>
                                <div class="d-flex gap-2">
                                    <a href="/animals/${animal.animalId}" class="btn btn-primary btn-sm">Learn More</a>
                                    <a href="/animals/update/${animal.animalId}" class="btn btn-warning btn-sm">
                                        <i class="fas fa-edit"></i> Edit
                                    </a>
                                    <a href="/animals/delete/${animal.animalId}" class="btn btn-danger btn-sm" 
                                       onclick="return confirm('Are you sure you want to delete ${animal.name}?')">
                                        <i class="fas fa-trash"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        <#else>
            <div class="alert alert-info text-center" role="alert">
                <h4><i class="fas fa-info-circle"></i> No marine animals found!</h4>
                <#if searchTerm??>
                    <p>No results for "${searchTerm}". <a href="/animals">View all animals</a></p>
                <#else>
                    <p>Start by <a href="/animals/new">adding your first marine animal</a>.</p>
                </#if>
            </div>
        </#if>
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
