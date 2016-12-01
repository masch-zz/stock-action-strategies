package org.masch.exercise.stock.validation;

import org.masch.exercise.stock.dto.StockActionDTO;
import org.masch.exercise.stock.dto.UserAcquisitionDTO;

public interface StockActionValidation {

    boolean check(StockActionDTO oldStocksActionsDTO, StockActionDTO newStocksActionsDTO, UserAcquisitionDTO userAcquisitionDTO);

}
