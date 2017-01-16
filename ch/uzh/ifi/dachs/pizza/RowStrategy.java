package ch.uzh.ifi.dachs.pizza;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrick on 15.01.17.
 */
public class RowStrategy implements PizzaStrategy {

	@Override
	public PizzaSolution computeSlices(Pizza pizza) {


		Pizza pizzaTransposed = new Pizza(
				pizza.getColumns(),
				pizza.getRows(),
				pizza.getIngredients(),
				pizza.getMax_cells());

		for(int i = 0; i<pizza.getRows(); i++){
			for(int j = 0; j<pizza.getColumns(); j++){
				if((pizza.getPizza())[i][j]) {
					pizzaTransposed.setCell(j, i, 'T');
				}
				else{
					pizzaTransposed.setCell(j, i, 'M');
				}
			}
		}

		PizzaSolution slices = computeSlicesIntern(pizza);
		PizzaSolution slicesTransposed = computeSlicesIntern(pizzaTransposed);

		System.out.println(slices.getTotalScore()-slicesTransposed.getTotalScore());

		if(slices.getTotalScore()>slicesTransposed.getTotalScore()){
			return slices;
		}

		List<Slice> slicesNeu = new ArrayList<Slice>();
		for(Slice slice : slicesTransposed.getSlices()){
			slicesNeu.add(new Slice(
					new Point(slice.getFirst().y, slice.getFirst().x),
					new Point(slice.getSecond().y, slice.getSecond().x)
			));
		}
		return new PizzaSolution(slicesNeu);

	}


		//boolean[][] arr = pizza.getPizza();


	public PizzaSolution computeSlicesIntern(Pizza pizza) {
		List<Slice> slices = new ArrayList<Slice>();
		Slice slice;
		int score = 0;
		for(int row = 0; row < pizza.getRows(); row++) {
			int leftColumn = 0;
			while (leftColumn < pizza.getColumns()) {
				int rightColumn = leftColumn + pizza.getMax_cells() - 1;
				if (rightColumn > pizza.getColumns() - 1) {
					rightColumn = pizza.getColumns() - 1;
				}
				slice = new Slice(new Point(row, leftColumn), new Point(row, rightColumn));
				score += slice.getNumCells();
				if (pizza.sliceIsValid(slice)) {
					slices.add(slice);
					leftColumn = rightColumn + 1;
				} else {
					if (slices.size() > 0) {
						Slice sliceToBeShortened = slices.get(slices.size() - 1);
						//System.out.println("sliceToBeShortened " + sliceToBeShortened);

						if (sliceToBeShortened.getFirst().x == row) {

							int sliceLength = sliceToBeShortened.getSecond().y - sliceToBeShortened.getFirst().y;

							for(int k = 1; k<=sliceLength-pizza.getIngredients()*2; k++) {

								Slice sliceShortend = new Slice(
										new Point(row, sliceToBeShortened.getFirst().y),
										new Point(row, sliceToBeShortened.getSecond().y - k)
								);
								Slice sliceNew = new Slice(
										new Point(row, sliceToBeShortened.getSecond().y-k+1),
										new Point(row, Math.min(
												sliceToBeShortened.getSecond().y + pizza.getMax_cells() - k,
												pizza.getColumns() - 1)
										)
								);
								if (pizza.sliceIsValid(sliceShortend) && pizza.sliceIsValid(sliceNew)) {
									slices.remove(slices.size() - 1);
									slices.add(sliceShortend);
									slices.add(sliceNew);
									leftColumn = sliceNew.getSecond().y;
									break;
								}
							}
						}
					}
					leftColumn++;
				}
			}
		}
		//System.out.print(String.format("Score: %d", score));
		//System.out.print(pizza.sliceIsValid(new Slice(new Point(1,0), new Point(1,4))));

		/*for(Slice sliceOut : slices){
			System.out.println(sliceOut);
		}*/
		return new PizzaSolution(slices);
	}
}
