package ESI.TP.Clinic.Controllers;

import ESI.TP.Clinic.Modules.orthophoniste.Orthophoniste;
import ESI.TP.Clinic.Modules.RDV.RendezVous;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;
import java.util.Map;

public class AgendaControllers {
    @FXML
    private TableView<RendezVous> appointmentsTable;

    @FXML
    private TableColumn<RendezVous, LocalDateTime> dateColumn;

    @FXML
    private TableColumn<RendezVous, String> durationColumn;

    @FXML
    private TableColumn<RendezVous, String> typeColumn;

    private Orthophoniste.Agenda agenda;

    // Setter for agenda
    public void setAgenda(Orthophoniste.Agenda agenda) {
        this.agenda = agenda;
        populateTable();
    }

    @FXML
    public void initialize() {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duree"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
    }

    private void populateTable() {
        if (appointmentsTable != null && dateColumn != null && durationColumn != null && typeColumn != null && agenda != null) {
            ObservableList<RendezVous> appointments = FXCollections.observableArrayList();

            for (Map.Entry<LocalDateTime, RendezVous> entry : agenda.getAgenda().entrySet()) {
                appointments.add(entry.getValue());
            }
            appointmentsTable.setItems(appointments);
            System.out.println(appointmentsTable);
        } else {
            System.err.println("FXML elements are not fully initialized or agenda is null.");
        }
    }
}
