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


		//boolean[][] arr = pizza.getPizza();

		List<Slice> slices = new ArrayList<Slice>();
		/*slices.add(new Slice(new Point(0, 0), new Point(2, 1)));
		slices.add(new Slice(new Point(0, 2), new Point(2, 2)));
		slices.add(new Slice(new Point(3, 4), new Point(3, 4)));
		System.out.println(pizza.sliceIsValid(new Slice(new Point(1, 3), new Point(2, 5))));*/

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
		return new PizzaSolution(slices);
	}
}
