import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MealPlanMantainComponent } from './meal-plan-mantain.component';

describe('MealPlanMantainComponent', () => {
  let component: MealPlanMantainComponent;
  let fixture: ComponentFixture<MealPlanMantainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MealPlanMantainComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MealPlanMantainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
