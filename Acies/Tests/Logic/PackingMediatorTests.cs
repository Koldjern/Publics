using Castle.Core.Logging;
using DataLayer.Files.Settings;
using LogicLayer.Packing.Decorations;
using Microsoft.Extensions.Logging;
using Models.Common;
using Models.Entities;
using Models.Entities.Settings;
using Moq;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Tests.Logic;

public class PackingMediatorTests
{
    [Fact]
    public void Calculate_Small_Order()
    {
        //arrange
        LocalSettingsData data = new LocalSettingsData()
        {
            PlacementSettings = new PlacementSettings
            {
                MaxLayers = 3,
                MaxSpots = 6,
                MaxTurnableWeight = 100,
                MaxHeightWidthFactor = 3,
                FactorOnlySingles = false,
                MaxHeightStacking = 1200,
                EndBoardThickness = 10,
                MaxStackElementWeight = 30,
                //Turn the above max height no stackable elements
                TurnMaxHeightNonStackables = true,
            }
        };
        Mock<ISettingsRepository> settings = new Mock<ISettingsRepository>();
        settings.Setup(set => set.GetSettings()).Returns(data);
        PackingMediator mediator = new PackingMediator(settings.Object);
        Pallet[] pallets = [new Pallet() { Dimensions = new Dimensions3D() { X = 1500, Y = 100, Z = 2000 }, Spots = 5, SpaceBetween = 60, MaxHeight = 3000, MaxWeight = 1250, Weight = 10, OverReach = 200 }];
        PlacedOrder order = new PlacedOrder()
        {
            Id = 1,
            Elements = new List<PlacedElement>() { new PlacedElement() { Id = 1, Stackable = true, Quantity = 7, Turnable = 0, Batch = "Test", Weight = 10, Dimensions = new Dimensions3D() { X = 750, Y = 100, Z = 130 } }, new PlacedElement() { Id = 2, Stackable = true, Quantity = 3, Turnable = 0, Batch = "Test", Weight = 100, Dimensions = new Dimensions3D() { X = 350, Y = 400, Z = 130 } } },
            Groups = new List<PlacedGroup>() { new PlacedGroup() { Elements = new List<PlacedElement>() { new PlacedElement() { Id = 3, Stackable = true, Quantity = 4, Turnable = 0, Batch = "Tester", Weight = 30, Dimensions = new Dimensions3D() { X = 1000, Y = 300, Z = 130 } },
            new PlacedElement() { Id = 4, Stackable = false, Quantity = 2, Turnable = 0, Batch = "Tester", Weight = 70, Dimensions = new Dimensions3D() { X = 100, Y = 1500, Z = 130 } }
            }}}
        };
        CalculationRequest request = new CalculationRequest(order, pallets) { GroupByBatch = true };
        //act

        //Heaviest can be added last, add myabe check if pos in spot switch 
        CalculationResult result = mediator.CalculatePacking(request);
        //assert
        Assert.True(result != null);
    }
}
