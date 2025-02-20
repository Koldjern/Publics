
using Moq;
using DataLayer.EF.Context;
using DataLayer.Pallets;
using Models.Entities;
using Microsoft.EntityFrameworkCore;

public class EFPalletRepositoryTestsMoq
{
    private readonly Mock<IPalletContext> _mockContext;
    private readonly Mock<DbSet<Pallet>> _mockDbSet;
    private readonly EFPalletRepository _repository;

    public EFPalletRepositoryTestsMoq()
    {
        _mockContext = new Mock<IPalletContext>();
        _mockDbSet = new Mock<DbSet<Pallet>>();
        _repository = new EFPalletRepository(_mockContext.Object);
    }

    [Fact]
    public void AddPallet_ShouldReturnTrue_WhenSaveSucceeds()
    {
        // Arrange
        var pallet = new Pallet
        {
            Id = Guid.NewGuid(),
            Description = "Test Pallet"
        };

        // Mock DbSet and its behavior
        _mockContext.Setup(c => c.Pallets()).Returns(_mockDbSet.Object);
        _mockContext.Setup(c => c.Save()).Returns(1); // Simulate successful save

        // Act
        var result = _repository.AddPallet(pallet);

        // Assert
        Assert.True(result); // Check if the result is true
        _mockDbSet.Verify(db => db.Add(It.Is<Pallet>(p => p == pallet)), Times.Once); // Verify Add is called
        _mockContext.Verify(c => c.Save(), Times.Once); // Verify Save is called
    }
    [Fact]
    public void DeletePallet_ShouldReturnTrue_WhenSaveSucceeds()
    {
        // Arrange
        var palletId = Guid.NewGuid();
        var pallet = new Pallet
        {
            Id = palletId,
            Description = "Test Pallet"
        };

        // Mock DbSet to simulate a pallet being found
        //simulate a empty pallet list
        var palletList = new List<Pallet> { pallet }.AsQueryable();
        //setting up dbset as queryable
        _mockDbSet.As<IQueryable<Pallet>>().Setup(m => m.Provider).Returns(palletList.Provider);
        _mockDbSet.As<IQueryable<Pallet>>().Setup(m => m.Expression).Returns(palletList.Expression);
        _mockDbSet.As<IQueryable<Pallet>>().Setup(m => m.ElementType).Returns(palletList.ElementType);
        _mockDbSet.As<IQueryable<Pallet>>().Setup(m => m.GetEnumerator()).Returns(palletList.GetEnumerator());

        // Mock context behavior
        _mockContext.Setup(c => c.Pallets()).Returns(_mockDbSet.Object);
        _mockContext.Setup(c => c.Save()).Returns(1); // Simulate successful save

        // Act
        var result = _repository.DeletePallet(palletId);

        // Assert
        Assert.True(result); // Confirm the deletion was successful
        _mockDbSet.Verify(db => db.Remove(It.Is<Pallet>(p => p.Id == palletId)), Times.Once); // Verify pallet removal

    }
   
    [Fact]
    public void UpdatePallet_ShouldReturnFalse_WhenPalletDoesNotExist()
    {
        // Arrange
        var palletId = Guid.NewGuid();
        var updatedPallet = new Pallet
        {
            Id = palletId,
            Description = "Updated Description",
            Weight = 200
        };

        // Mock DbSet to simulate an empty pallet list
        var palletList = new List<Pallet>().AsQueryable();
        _mockDbSet.As<IQueryable<Pallet>>().Setup(m => m.Provider).Returns(palletList.Provider);
        _mockDbSet.As<IQueryable<Pallet>>().Setup(m => m.Expression).Returns(palletList.Expression);
        _mockDbSet.As<IQueryable<Pallet>>().Setup(m => m.ElementType).Returns(palletList.ElementType);
        _mockDbSet.As<IQueryable<Pallet>>().Setup(m => m.GetEnumerator()).Returns(palletList.GetEnumerator());

        // Mock context behavior
        _mockContext.Setup(c => c.Pallets()).Returns(_mockDbSet.Object);

        // Act
        var result = _repository.UpdatePallet(updatedPallet);

        // Assert
        Assert.False(result); // Confirm the update failed
        _mockContext.Verify(c => c.Save(), Times.Never); // Verify Save was not called
    }
    [Fact]
    public void GetPallet_ShouldReturnCorrectPallet_WhenPalletExists()
    {
        // Arrange
        var palletId = Guid.NewGuid();
        var pallet = new Pallet
        {
            Id = palletId,
            Description = "Test Pallet"
        };

        // Mock DbSet to simulate a collection with the specific pallet
        var palletList = new List<Pallet> { pallet }.AsQueryable();
        _mockDbSet.As<IQueryable<Pallet>>().Setup(m => m.Provider).Returns(palletList.Provider);
        _mockDbSet.As<IQueryable<Pallet>>().Setup(m => m.Expression).Returns(palletList.Expression);
        _mockDbSet.As<IQueryable<Pallet>>().Setup(m => m.ElementType).Returns(palletList.ElementType);
        _mockDbSet.As<IQueryable<Pallet>>().Setup(m => m.GetEnumerator()).Returns(palletList.GetEnumerator());

        // Mock the context to return the DbSet
        _mockContext.Setup(c => c.Pallets()).Returns(_mockDbSet.Object);

        // Act
        var result = _repository.GetPallet(palletId);

        // Assert
        Assert.NotNull(result); // Ensure a pallet is returned
        Assert.Equal(palletId, result.Id); // Check the ID matches
        Assert.Equal("Test Pallet", result.Description); // Check the description matches
    }


}



